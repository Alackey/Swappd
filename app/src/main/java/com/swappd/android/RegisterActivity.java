package com.swappd.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;

public class RegisterActivity extends AppCompatActivity {

    // Elements
    private EditText usernameField;
    private EditText emailField;
    private EditText passwordField;
    private Button registerButton;

    private CognitoUserPool userPool;
    private CognitoUserAttributes userAttributes;
    private SignUpHandler signupCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ClientConfiguration clientConfiguration = new ClientConfiguration();

        // Create a CognitoUserPool object to refer to your user pool
        userPool = new CognitoUserPool(
                getApplicationContext(),
                BuildConfig.AWS_COGNITO_POOL_ID,
                BuildConfig.AWS_COGNITO_CLIENT_ID,
                BuildConfig.AWS_COGNITO_CLIENT_SECRET,
                clientConfiguration
        );

        signupCallback = new SignUpHandler() {

            @Override
            public void onSuccess(CognitoUser cognitoUser, boolean userConfirmed, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                // Sign-up was successful
                Toast.makeText(getApplicationContext(), "Sign up complete", Toast.LENGTH_LONG).show();


                // Check if this user (cognitoUser) needs to be confirmed
                if(!userConfirmed) {
                    // This user must be confirmed and a confirmation code was sent to the user
                    // cognitoUserCodeDeliveryDetails will indicate where the confirmation code was sent
                    // Get the confirmation code from user
                }
                else {
                    // The user has already been confirmed
                }
            }

            @Override
            public void onFailure(Exception exception) {
                // Sign-up failed, check exception for the cause
            }
        };

        // Create a CognitoUserAttributes object and add user attributes
        userAttributes = new CognitoUserAttributes();

        usernameField = (EditText) findViewById(R.id.usernameText);
        emailField = (EditText) findViewById(R.id.emailText);
        passwordField = (EditText) findViewById(R.id.passwordText);
        registerButton = (Button) findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Adding user's email address
                userAttributes.addAttribute("email", emailField.getText().toString());

                userPool.signUpInBackground(
                        usernameField.getText().toString(),
                        passwordField.getText().toString(),
                        userAttributes, null,
                        signupCallback);
            }
        });



    }
}
