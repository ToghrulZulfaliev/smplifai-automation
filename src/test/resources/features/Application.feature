@application  @smoke @regression
Feature: Yeni Application prosesi


  Scenario: İstifadəçi yeni application yaradır, sənəd yükləyir və View bölməsində sənədi yoxlayır

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

    And Application uğurla yaradılmalıdır
    And İstifadəçi Dashboard səhifəsinə qaytarılmalıdır
    And Application list-də yeni application görünməlidir

    And İstifadəçi yeni application üçün View düyməsinə klik edir

    And Yüklənmiş Passport sənədi görünməlidir
    When Yüklənmiş Visa sənədi görünməlidir
    Then Yüklənmiş I-20 sənədi görünməlidir


  @download
  Scenario: İstifadəçi application üçün Download səhifəsini açır

    Given İstifadəçi sistemə login olub Dashboard səhifəsindədir

    When İstifadəçi application üçün Download düyməsinə klik edir

    Then Downloads səhifəsi açılmalıdır


  @delete
  Scenario: İstifadəçi application-ı uğurla silir

    Given İstifadəçi sistemə login olub Dashboard səhifəsindədir

    When İstifadəçi application üçün Delete düyməsinə klik edir

    Then Delete confirmation pəncərəsi açılmalıdır

    And İstifadəçi silinməni təsdiqləyir

    Then Delete confirmation pəncərəsi bağlanmalıdır