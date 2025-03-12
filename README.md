# TMInterview (Wiki is not free for Private Repo)
Middleware for Assessment TM (Logistic Services)

##Tech Version:
- Java - Java 17
- Springboot- 3.4.3
- Docker Version- 27.5.1

# Testing API Middleware

## 1️⃣ Prerequisites
- Docker installed
- Postman installed
- Cloned the code to Intellij
- Build Jar (mvn clean install // mvn clean install -DskipTests)

## 2️⃣ Running the Middleware
- Run this command: 'docker-compose up --build' in the project's path. (To run both redis and app containers)

## Testing API with Postman
- Paste the curl below in the bar
```
curl --location 'http://localhost:8080/abc/logistics/rate' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=79C78F2A30550016A2267DE9A6691556' \
--data '{
  "origin_country": "MY",
  "origin_state": "Melaka",
  "origin_postcode": "83300",
  "destination_country": "MY",
  "destination_state": "Kuala Lumpur",
  "destination_postcode": "52000",
  "length": 12,
  "width": 12,
  "height": 34,
  "selected_type": 2,
  "parcel_weight": 16,
  "document_weight": 24,
"shipping_rates_type": "domestic",
"shipping_type": "EZ",
"item_value": 0,
  "couriers": ["citylink","jnt"]
}'
```
- Can change the value of couriers, to have limited couriers eg: "couriers": ["citylink"]
```
curl --location 'http://localhost:8080/abc/logistics/rate' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=79C78F2A30550016A2267DE9A6691556' \
--data '{
  "origin_country": "MY",
  "origin_state": "Melaka",
  "origin_postcode": "83300",
  "destination_country": "MY",
  "destination_state": "Kuala Lumpur",
  "destination_postcode": "52000",
  "length": 12,
  "width": 12,
  "height": 34,
  "selected_type": 2,
  "parcel_weight": 16,
  "document_weight": 24,
"shipping_rates_type": "domestic",
"shipping_type": "EZ",
"item_value": 0,
  "couriers": ["citylink"]
}'
```
- Response: ![image](https://github.com/user-attachments/assets/b9e2a92a-d96d-4979-a37b-c671e646daad)


## Swagger
- http://localhost:8080/swagger-ui/index.html
- ![image](https://github.com/user-attachments/assets/48612ab4-eaf1-4466-bfba-19a36cc0d594)


## API Security
CORS
  - Add another key:value in the header request (Origin: https://notallowing.com)
  - ![image](https://github.com/user-attachments/assets/5e2fe79c-cc32-409e-a6d7-3e217862ba2f)
  - Will get Cors error since Im allowing only https://tm-abc.com
Hiding Token and Key
  - Check Application.properties for separating key and values to be hide

## Logging
- Logging the Request and Response as well as the Header during Feign Call to use for debugging
- Eg: ![image](https://github.com/user-attachments/assets/a29ac0bc-97b3-4744-9247-ed39b5fc9a4b)

 
## Unit Testing
- Unit testing for main services which are CityLinkLogisticServiceTest and JntLogisticServiceTest
- ![image](https://github.com/user-attachments/assets/5c75c871-5184-42d1-be11-06ea90247b6c)


  

