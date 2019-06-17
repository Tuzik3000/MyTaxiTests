Feature: Finding photos object User Samantha

    Scenario: Check that Samantha have around 500 photos in her albums
        Given username Samantha is found
        When album collection for username Samantha is extracted
        And photos collection for username Samantha is extracted
        Then photos collection for username Samantha should have 500 photos
