Feature: Show how to generate data on run time

   # Please note, it is a real scenario, the execution will passed
#-------------------------------------------------------------------------------------------
  Scenario:Successfully generate data on run time


    And   I want to call API Endpoint "{{demo}}"
    And   I provide basePath as "/demo/%randomAlpha(10)%"
    And   I provide form data as data Table:
      |data1     |%daysAhead(2)%                  |
      |data2     |%randomString(10)%              |
      |data5     |%daysAhead(2,MM/dd/yyyy)%       |
      |data6     |%yearsAhead(100,MM/dd/yyyy)%    |


    And   I provide body as raw JSON file "data/payloads/demo.json"
    And   I set JSON body node to String:
      |applicant.firstName |%randomString(10)%         |
      |appDate             |"%yearsAgo(25,MM-dd-yyyy)%"|

    And I add JSON node "email" to Parent node "" as String ""%randomEmail%""
    And I set JSON body node "applicant.lastName" to ""%randomAlpha(3)%""

    And  I provide headers as data Table:

      |header|%randomString(10, ABC 567^&*#$%_*&)%|

    And I provide query parameters as data Table:

      |date|%daysAhead(2)%|

    And I provide path variables as data Table:

      |id |%randomNumberLessThan(50)%|


    And   I send "GET" request
    Then  Response has Status code: "404"






