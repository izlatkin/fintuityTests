How To:

1. Run all Tests
>mvn test
with report generation with only  tests
>mvn surefire-report:report -DshowSuccess=false

2. Run single test
mvn -Dtest=LoginSimple test
3. Run single testcase
mvn -Dtest=LoginSimple#loginWithExistingCredentials test


Prod:
    backoffice: backoffice.fintuity.com
    Portal: http://portal.fintuity.com

Pre-prod:
    backoffice: https://preprod-app.fintuity.com/admin
    Portal: https://preprod-app.fintuity.com/portal/login

Testing:
    backoffice: https://fintuity.hiendsys.ru/admin/login
    Portal: https://fintuity.hiendsys.ru/portal/login


Registration tests:
 mvn -Dtest=RegistrationTest#reg_new_user_and_activate test
 mvn -Dtest=RegistrationTest#reg_new_user_activate_backoffice_confirm test

 Login Test
 mvn -Dtest=LoginSimpleTest#loginWithExistingCredentials test



