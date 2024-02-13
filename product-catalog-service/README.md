# Product Catalog Service

this is the product catalog service that stores and exposes all the published products.

## APIs
springdoc library is a dependency to generate the api documentation using openapi and swagger UI. check below urls: <br/> 
http://HOST:PORT/v3/api-docs <br/>
http://HOST:PORT/swagger-ui/index.html

## Security

Security Configuration is in ```SecurityConfiguration.java``` class <br/>
```@EnableWebSecurity``` is to enable the spring security start <br/>
```@EnableMethodSecurity``` is required to enable the security configuration on the method level <br/>
```requestMatchers("/**").permitAll())``` allow any requests to the services without authentication <br/>
```@PreAuthorize("hasRole('ADMIN')")``` an annotation on the create service which permit only authorized users with ADMIN permission to create a new product

