
Feature: Second Test Set
  @second
#-----------------------------------------------------------------------------------------------------------------------
  Scenario: Google

    Given I want to call API Endpoint "{{deployment_url}}"
    And I provide headers as csv file "data/headers.csv"
    And I provide body as raw JSON file "data/request.json"
    And I set JSON body node "field" to ""%randomAlpha(10)%""
    When I send "GET" request
    Then Response has Status code: "200"
    And I store Response header "X-Frame-Options" as "options" in the scenario scope

    And I clear my previous API call

    Given I want to call API Endpoint "{{deployment_url}}/<options>"
    And I provide headers as csv file "data/headers.csv"
    And I provide headers as data Table:
    |options     |<options>                           |
    |header1     |%yearsAgo(100)%                     |
    |header2     |%daysAgo(0)%                        |
    |header3     |%daysAhead(2)%                      |
    |header4     |%yearsAhead(100)%                   |
    |header5     |%randomAlpha(10)%                   |
    |header6     |%randomString(10, ABC 567^&*#$%_*&)%|
    |header7     |%randomNumber(10)%                  |
    |header8     |%randomEmail%                       |
    |header9     |%randomString(10)%                  |

    When I send "GET" request
    Then Response has Status code: "400"






