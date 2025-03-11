package com.SPL_middleware.assignment.service;

import com.SPL_middleware.assignment.dto.JntRateRequest;
import com.SPL_middleware.assignment.dto.RateResponse;
import com.SPL_middleware.assignment.feign.JnTClient;
import feign.Response;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JntLogisticService implements LogisticService {
    private static final Logger logger = LoggerFactory.getLogger(JntLogisticService.class);

    @Value("${jnt.cookies}") // Values taken from the GET API curl in the JnT Website
    private String cookies;
    private final JnTClient jnTClient;
    private final CacheService cacheService;

    public JntLogisticService(JnTClient jnTClient,CacheService cacheService) {
        this.jnTClient = jnTClient;
        this.cacheService= cacheService;
    }

    public RateResponse getRate(Object request) {
        try {
            JntRateRequest jntRateRequest = (JntRateRequest) request;
            logger.info("[Request][J&T][GET]: " + jntRateRequest);

            //Flow
            //1- Check whether same key exist in redis cache
            String cacheKey = generateCacheKey(jntRateRequest);
            Object cachedRate = cacheService.getFromCache(cacheKey);
            if (cachedRate != null) {
                logger.info("Retrieved from J&T cache: " + cachedRate);
                return new RateResponse("J&T", (Double) cachedRate);
            }

            //2- Else, call API
            //1st api
            Response tokenResponse = jnTClient.fetchToken(cookies);

            String token = extractToken(tokenResponse);
            String cookie = extractCookies(tokenResponse);
            logger.info("Token: " + token);
            logger.info("Cookie: " + cookie);

            String requestBody = mapToUrlEncoded(jntRateRequest, token);
            logger.info("[Request][J&T][POST]:"+requestBody);

            //2nd api
            Response htmlResponse = jnTClient.getRate(cookie,requestBody);

            logger.info("Doing extraction");
            String finalRate = extractRateFromHtml(htmlResponse);
            double finalRateConverted= Double.parseDouble(finalRate);
            logger.info("JnT Price Rate is: " + finalRate);

            cacheService.saveToCache(cacheKey, finalRateConverted);

            return new RateResponse("J&T", finalRateConverted);
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            return new RateResponse("J&T", 0.0);
        }
    }

    private String generateCacheKey(JntRateRequest request) {
        return String.format("jnt:rate:%s:%s:%s:%s:%.2f:%.2f:%.2f:%.2f:%.2f",
                request.getShippingRatesType(),
                request.getSenderPostcode(),
                request.getReceiverPostcode(),
                request.getDestinationCountry(),
                request.getWeight(),
                request.getLength(),
                request.getWidth(),
                request.getHeight(),
                request.getItemValue()
        );
    }

    private String mapToUrlEncoded(JntRateRequest request, String token) {
        return String.format(
                "_token=%s&shipping_rates_type=%s&sender_postcode=%s&receiver_postcode=%s&destination_country=%s&shipping_type=%s&weight=%.2f&length=%.2f&width=%.2f&height=%.2f&item_value=%.2f",
                token,
                request.getShippingRatesType(),
                request.getSenderPostcode(),
                request.getReceiverPostcode(),
                request.getDestinationCountry(),
                request.getShippingType(),
                request.getWeight(),
                request.getLength(),
                request.getWidth(),
                request.getHeight(),
                request.getItemValue()
        );
    }

    private String extractToken(Response response) {
        try {
            String body = extractHtml(response);
            Document doc = Jsoup.parse(body);
            Element tokenElement = doc.selectFirst("input[name=_token]");
            return tokenElement != null ? tokenElement.attr("value") : "";
        } catch (IOException e) {
            logger.error("IO Exception While Extracting HTML element for Token");
            throw new RuntimeException("Failed to extract token", e);
        }
    }
    private String extractHtml(Response response) throws IOException {
        try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }


    private String extractCookies(Response response) {
        return response.headers().getOrDefault("Set-Cookie", List.of())
                .stream()
                .map(cookie -> cookie.split(";")[0])
                .collect(Collectors.joining("; "));
    }



    private String extractRateFromHtml(Response response) throws IOException {
        String htmlBody;

        if (response.body() == null) {
            logger.info("Response body is null.");
            return "No HTML Response received";
        }
        System.out.println("First Checkpoint");

        try (InputStream inputStream = response.body().asInputStream()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                htmlBody = reader.lines().collect(Collectors.joining("\n"));
            }
        }
        System.out.println("Second Checkpoint");

        Document doc = Jsoup.parse(htmlBody);
        System.out.println("Third Checkpoint");

        // In desktop layout, its in first row, second column
        // In mobile layout, its in second row, first cell
        try {
            // Try desktop table
            Element desktopTable = doc.select("div.d-none.d-sm-block table").first();
            if (desktopTable != null) {
                Elements rows = desktopTable.select("tbody tr");
                if (!rows.isEmpty()) {
                    Element parcelRow = rows.first();
                    Elements cells = parcelRow.select("td");
                    if (cells.size() >= 2) {
                        String rate = cells.get(1).text().trim();
                        System.out.println("Found rate in desktop table: " + rate);
                        return rate;
                    }
                }
            }

            // Try mobile table
            Element mobileTable = doc.select("div.d-block.d-sm-none table").first();
            if (mobileTable != null) {
                Elements rows = mobileTable.select("tbody tr");
                if (rows.size() >= 2) {
                    Element shippingRow = rows.get(1);
                    Elements cells = shippingRow.select("td");
                    if (!cells.isEmpty()) {
                        String rate = cells.first().text().trim();
                        System.out.println("Found rate in mobile table: " + rate);
                        return rate;
                    }
                }
            }

            logger.info("Could not find shipping rate in either table.");
            return "No Rate";
        } catch (Exception e) {
            logger.error("Exception While Extracting HTML element for J&T Rate:"+e.getMessage());
            return "No Rate";
        }
    }
}
