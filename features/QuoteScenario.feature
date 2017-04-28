@f1
Feature: Test making Quote through Dimensional Freight Quote

  Background: 
    Given setup browser "chrome" and navigate " https://mystage.yrc.com/"
    Given Login through username"uyr348b" and password"yrcfreight2" and navigate to DFQ page

  @case1
  Scenario Outline: N - (Ship From (Shipper)) - Credit Card
    Then fill requester information with your location <location>
    Then select role of <role>
    Then select pick up date
    Then enter ship from location type <from location type> and enter zip <from location zip>
    Then enter ship to location type <to location type> and enter zip <to location zip>
    Then set handling unit "Carton" and shipment size with L "48" W "48" H "48" # "1" WEIGHT "500"
    Then click next and kick off quote summary page
    Then select the quote and kick off shipment detail page
    Then fill the shipper information with <shipper company name> and <shipper address> and <shipper email>
    Then fill the consignee information <consignee company name> and <consignee address> and <consignee email>
    Then fill shipment product information with piece of count <count> select Unit of measurement <unit> descroption "this is fragile"
    Then fill pick up information with contact name <contact name> number <contact number>
    Then click next and kick off terms&condition page
    Then accept terms and condition and switch to payment window
    And select payment card AND confirm payment and kick off the confirmation page
    And wrap the quote id, pro number and pick up reference number to the excel report

    Examples: 
      | location                                                | role | from location type               | from location zip | to location type                 | to location zip | shipper company name | shipper address  | shipper email | consignee company name | consignee address | consignee email            | count | unit   | contact name | contact number |
      | TRUE VALUE at 225 S FRONT ST in CENTRAL POINT, OR 97502 | SH   | Commercial with forklift or dock |                   | Commercial with forklift or dock |           64114 | yrc freight          | 10990 roe ave,mo |               | sprint inc             | 6480 Sprint Pkwy  | Kamran.Syed@YRCFreight.com |     2 | Carton | Nica         |     2320909989 |

  @case2
  Scenario Outline: M - (Ship To (Consignee)) - Credit Card
    Then fill requester information with your location <location>
    Then select role of <role>
    Then select pick up date
    Then enter ship from location type <from location type> and enter zip <from location zip>
    Then enter ship to location type <to location type> and enter zip <to location zip>
    Then set handling unit "Carton" and shipment size with L "20" W "23" H "12" # "2" WEIGHT "203"
    Then click next and kick off quote summary page
    Then select the quote and kick off shipment detail page
    Then fill the shipper information with <shipper company name> and <shipper address> and <shipper email>
    Then fill the consignee information <consignee company name> and <consignee address> and <consignee email>
    Then fill shipment product information with piece of count <count> select Unit of measurement <unit> descroption "this is fragile"
    Then fill pick up information with contact name <contact name> number <contact number>
    Then click next and kick off terms&condition page
    Then accept terms and condition and switch to payment window
    And select payment card AND confirm payment and kick off the confirmation page
    And wrap the quote id, pro number and pick up reference number to the excel report

    Examples: 
      | location                                                | role | from location type               | from location zip | to location type                 | to location zip | shipper company name | shipper address  | shipper email                 | consignee company name | consignee address | consignee email | count | unit   | contact name | contact number |
      | TRUE VALUE at 225 S FRONT ST in CENTRAL POINT, OR 97502 | CN   | Commercial with forklift or dock |             64112 | Commercial with forklift or dock |                 | yrc freight          | 10990 roe ave,mo | Nica.Marroquin@YRCFreight.com | sprint inc             | 6480 Sprint Pkwy  |                 |     2 | Carton | Nica         |     2320909989 |
