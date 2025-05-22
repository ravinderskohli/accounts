Feature: Verify the Accounts Service

    Scenario: Clients makes call to GET accounts
        Given the client calls contact-info
        Then the client receives status code of 200