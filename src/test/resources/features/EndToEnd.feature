@e2e
Feature: Smplifai End-to-End biznes prosesinin yoxlanılması

  Scenario: İstifadəçi login olur, application yaradır və yüklədiyi sənədləri yoxlayır

    Given İstifadəçi sistemə login olub Dashboard səhifəsindədir

    When İstifadəçi New Application düyməsinə klik edir
    Then New Application pəncərəsi açılmalıdır

    And İstifadəçi düzgün telefon nömrəsi daxil edir
    And İstifadəçi Choose düyməsinə klik edir
    And Application Enrollment Agreement pəncərəsi açılmalıdır

    And İstifadəçi Passport sənədi üçün Upload sahəsinə klik edir
    And İstifadəçi Passport sənədini yükləyir

    And İstifadəçi Visa sənədi üçün Upload sahəsinə klik edir
    And İstifadəçi Visa sənədini yükləyir

    And İstifadəçi I-20 sənədi üçün Upload sahəsinə klik edir
    And İstifadəçi I-20 sənədini yükləyir

    And İstifadəçi Submit düyməsinə klik edir

    Then Application uğurla yaradılmalıdır
    And İstifadəçi Dashboard səhifəsinə qaytarılmalıdır
    And Application list-də yeni application görünməlidir

    When İstifadəçi yeni application üçün View düyməsinə klik edir

    Then Yüklənmiş Passport sənədi görünməlidir
    And Yüklənmiş Visa sənədi görünməlidir
    And Yüklənmiş I-20 sənədi görünməlidir