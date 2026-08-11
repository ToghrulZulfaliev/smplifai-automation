@login @smoke @regression
Feature: Smplifai sisteminə giriş

  Scenario: İstifadəçi düzgün məlumatlarla sistemə uğurla daxil olur

    Given İstifadəçi Smplifai giriş səhifəsindədir
    When İstifadəçi düzgün email daxil edir
    And İstifadəçi düzgün şifrə daxil edir
    And İstifadəçi "Log In" düyməsinə klik edir
    Then İstifadəçi sistemə uğurla daxil olmalıdır