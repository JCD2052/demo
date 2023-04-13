Feature: JsonPlaceholderAPI service test cases.

  Scenario: Get all albums and check a response:
    When I perform GET response to albums service I receive and save a 'response'
    Then I check that a 'response' status code is '200'
    And I check that a 'response' content type is 'application/json; charset=utf-8'
    And I check that a 'response' size is '100'

  Scenario: Get all photos from certain album and check a response:
    When I perform GET response to photos service with album id '1' I receive and save a 'response'
    Then I check that a 'response' status code is '200'
    And I check that a 'response' content type is 'application/json; charset=utf-8'
    And I check that a 'response' size is '50'
    And I check that a 'response' all photos objects have the same album id '1'