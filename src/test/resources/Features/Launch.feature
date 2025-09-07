Feature: Apollo 24/7 Website Launch

  Scenario Outline: Verify Apollo 24/7 launches in supported browsers
    Then The Apollo homepage should be displayed

    Examples:
      | browser |
      | chrome  |
      | firefox |
