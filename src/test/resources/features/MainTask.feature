Feature: Finding objects related to User Samantha

    Scenario: Check that Samantha comments have same email address as her profile
        Given username Samantha is found
        When posts collection for username Samantha is extracted
        And comments collection for username Samantha is extracted
        Then comments collection for username Samantha should have correct email
