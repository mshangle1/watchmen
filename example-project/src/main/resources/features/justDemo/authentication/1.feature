Feature: How to authenticate request

 # Please note, it is just an example scenario, the execution will fail

#-------------------------------------------------------------------------------------------

  Scenario: 1. Successfully authenticate request using Bearer token and new implemented step

          # Expected: consumer_key, consumer_secret and oauth_URL are provided on the config.properties
          # New step "I requested an access_token" is implemented under steps/Authentication
          # New step "I requested an access_token" requests access_token from oauth_URL and store token as "access_token" in the Scenario scope

    Given I requested an access_token
    And   I want to call API Endpoint "{{demo}}"
    And   I provide my token "<access_token>" as a Bearer token on Authorization header
    And   I provide headers as data Table:

      |Content-Type|application/json; charset=utf-8|

    And   I send "GET" request
    Then  Response has Status code: "200"


 #-------------------------------------------------------------------------------------------

  Scenario: 2. Successfully authenticate request using Basic Auth

          # Expected: user and pwd are provided on the config.properties

    And   I want to call API Endpoint "{{demo}}"
    And   I provide user name "{{user}}" and password "{{pwd}}" as a Basic authentication on Authorization header
    And   I provide headers as data Table:

      |Content-Type|application/json; charset=utf-8|

    And    I send "GET" request
    Then   Response has Status code: "200"

    Then   Response has Status code: "200"