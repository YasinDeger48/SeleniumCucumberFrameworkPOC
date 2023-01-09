@wipSele
Feature: Login Scenarios

  @wip6
  Scenario: OrangeHRM Login Scenario
    Given "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" sitesini aç.
    When Login syfasında olduğunu doğrula.
    And Enter username as "Admin"
    And Enter password as "admin123"
    And Click Login button
    Then Verify on login page


  Scenario: OrangeHRM Login Scenario 2
    Given "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" sitesini aç.
    When Login syfasında olduğunu doğrula.
    And Enter username as "Admin"
    And Enter password as "admin123"
    And Click Login button
    Then Verify on login page

  Scenario: OrangeHRM Login Scenario 3
    Given "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" sitesini aç.
    When Login syfasında olduğunu doğrula.
    And Enter username as "Admin"
    And Enter password as "admin123"
    And Click Login button
    Then Verify on login page

  @wip1
  Scenario Outline: MoodleDemo sandbox Login with diff credentials
    Given "https://sandbox.moodledemo.net/" sitesini aç.
    When Login linkine tıkla.
    Then Giriş sayfasında olduğunu doğrula.
    And "<username>" username gir.
    And "<password>" password gir.
    Then Login butonuna tıkla.
    Then "<dashboardTitle>" title'ın görüldüğünü doğrula.

    Examples:
      | username | password | dashboardTitle          |
      | admin    | sandbox  | Welcome back, Admin! 👋   |
      | manager  | sandbox  | Welcome back, Max! 👋   |
      | teacher  | sandbox  | Welcome back, Terri! 👋 |
      | student  | sandbox  | Welcome back, Sam! 👋   |


