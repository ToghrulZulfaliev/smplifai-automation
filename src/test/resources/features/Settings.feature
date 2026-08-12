@settings @regression
Feature: Settings funksionallığı

  Scenario: İstifadəçi Dark theme seçir
    Given İstifadəçi Settings səhifəsindədir
    And İstifadəçi theme bölməsinə scroll edir
    When İstifadəçi Dark theme seçir
    Then Səhifə Dark theme rejiminə keçməlidir