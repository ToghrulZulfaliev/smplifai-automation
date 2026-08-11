@register
Feature: Smplifai sisteminə qeydiyyat

  Scenario: İstifadəçi düzgün məlumatlarla uğurla qeydiyyatdan keçir

    Given İstifadəçi Smplifai giriş səhifəsindədir
    When İstifadəçi "Sign up here" linkinə klik edir
    Then İstifadəçi qeydiyyat səhifəsinə yönləndirilməlidir

    When İstifadəçi qeydiyyat üçün düzgün ad daxil edir
    And İstifadəçi qeydiyyat üçün düzgün soyad daxil edir
    And İstifadəçi qeydiyyat üçün düzgün email daxil edir
    And İstifadəçi qeydiyyat üçün düzgün telefon nömrəsi daxil edir
    And İstifadəçi qeydiyyat üçün düzgün şifrə daxil edir
    And İstifadəçi qeydiyyat üçün şifrəni təkrar daxil edir
    And İstifadəçi Terms of Use və Privacy Policy checkbox-ını seçir
    And İstifadəçi Create account düyməsinə klik edir

    Then İstifadəçi sistemdə uğurla qeydiyyatdan keçməlidir