# spring boot data ldap

## JWT request filters

* [DZione JWT spring](https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world)
* [JWT and roles](https://medium.com/@hantsy/protect-rest-apis-with-spring-security-and-jwt-5fbc90305cc5)
* [JWT resttemplate](https://www.kingsware.de/2019/07/20/spring-boot-passthrough-jwt-with-resttemplate/)
* [testtemplate interceptor](https://stackoverflow.com/questions/46729203/propagate-http-header-jwt-token-over-services-using-spring-rest-template)
* []()
* []()

 think it is better to add the interceptor specifically to the RestTemplate, like this:
 
 class RestTemplateHeaderModifierInterceptor(private val authenticationService: IAuthenticationService) : ClientHttpRequestInterceptor {
    override fun intercept(request: org.springframework.http.HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        if (!request.headers.containsKey("Authorization")) {
            // don't overwrite, just add if not there.
            val jwt = authenticationService.getCurrentUser()!!.jwt
            request.headers.add("Authorization", "Bearer $jwt")
        }
        val response = execution.execute(request, body)
        return response
    }
}

And add it to the RestTemplate like so:

@Bean
fun restTemplate(): RestTemplate {
    val restTemplate = RestTemplate()
restTemplate.interceptors.add(RestTemplateHeaderModifierInterceptor(authenticationService)) // add interceptor to send JWT along with requests.
    return restTemplate
}


That way, every time you need a RestTemplate you can just use autowiring to get it. You do need to implement the AuthenticationService still to get the token from the TokenStore, like this:


val details = SecurityContextHolder.getContext().authentication.details
if (details is OAuth2AuthenticationDetails) {
   val token = tokenStore.readAccessToken(details.tokenValue)
   return token.value
}


## LDAP TEMPLATE

* [Spring LDAP Overview](https://www.baeldung.com/spring-ldap)
* [Guide to Spring Data LDAP](https://www.baeldung.com/spring-data-ldap)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [gihub example](https://github.com/eugenp/tutorials/tree/master/spring-ldap)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [ldapauthenticationjwttoken](https://github.com/merugu/springsecurity/tree/master/ldapauthenticationjwttoken)
* [ldap and jwt aut, but outdated but good stuff](https://github.com/SNCF-SIV/spring-security-rest-jwt-ldap)
* [userdetailservice 1](https://dzone.com/articles/spring-security-with-spring-boot-20-userdetailsser)
* [userdetailservice 2](https://www.boraji.com/spring-security-5-custom-userdetailsservice-example)
* [userdetailservice 3](https://javainterviewpoint.com/spring-security-custom-userdetailsservice-example/)
