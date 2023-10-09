This is how to use the TipCalculator android app in the Repository:

But first some Specifications:
- Supports a minimum SDK of 22 and has a target SDK of 34 as for the Google Play Requirements:
    https://developer.android.com/google/play/requirements/target-sdk
- Uses View Binding opposed to standard Data Binding for ease of coding and readability

App Use: 
This app is meant to allows users to calculate individual tip and total amounts each person in their party is responsible for
The app calculates common tip ranges and displays them underneath the button along with the total price per person for each tip size

- The app contain two editable fields where user can input a Check Amount and a Party Size
    -  The Check Amount only integer or double values
    -  The Party Size field accepts integer values only
- Once user inputs data into these fields they press the button that says "Compute Tip" to reveal the calculated tips and total of varying percentages
- The resepective field(s) will turn RED and issue a Toast to alert user to write a valid input when :
    - The user inputs nothing in both or either field
    - The user inputs in PartySize is a double or a negative number or NAN
    - The user inputs a negative number(Integer/Double) or NAN
 
written by Camille Cajas Yadaicela
