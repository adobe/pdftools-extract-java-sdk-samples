/*
 * Copyright 2020 Adobe
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute this file in
 * accordance with the terms of the Adobe license agreement accompanying
 * it.
 */

package com.adobe.platform.operation.samples.extractpdf;

import java.io.IOException;

import com.adobe.platform.operation.pdfops.constants.PDFElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.platform.operation.ExecutionContext;
import com.adobe.platform.operation.auth.Credentials;
import com.adobe.platform.operation.exception.SdkException;
import com.adobe.platform.operation.exception.ServiceApiException;
import com.adobe.platform.operation.exception.ServiceUsageException;
import com.adobe.platform.operation.io.FileRef;
import com.adobe.platform.operation.pdfops.ExtractPDFOperation;

/**
 * This sample illustrates how to extract Text Information from PDF.
 * <p>
 * Refer to README.md for instructions on how to run the samples & understand output zip file.
 */
public class ExtractTextInfoFromPDFWithInMemoryAuthCredentials {

    // Initialize the logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractTextInfoFromPDFWithInMemoryAuthCredentials.class);

    public static void main(String[] args) {

        try {

            /*
            Initial setup, create credentials instance.
            Replace the values of CLIENT_ID, CLIENT_SECRET, ORGANIZATION_ID and ACCOUNT_ID with their corresponding values
            present in the pdftools-api-credentials.json file and PRIVATE_KEY_FILE_CONTENTS with contents of private.key file
            within the zip file received from Adobe.
            */
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .withClientId("CLIENT_ID")
                    .withClientSecret("CLIENT_SECRET")
                    .withPrivateKey("PRIVATE_KEY_FILE_CONTENTS")
                    .withOrganizationId("ORGANIZATION_ID")
                    .withAccountId("ACCOUNT_ID")
                    .build();

            //Create an ExecutionContext using credentials and create a new operation instance.
            ExecutionContext executionContext = ExecutionContext.create(credentials);
            ExtractPDFOperation extractPdfOperation = ExtractPDFOperation.createNew();

            // Set operation input from a source file.
            FileRef source = FileRef.createFromLocalFile("src/main/resources/extractPdfInput.pdf");
            extractPdfOperation.setInputFile(source);
            extractPdfOperation.addElementToExtract(PDFElementType.TEXT);

            // Execute the operation.
            FileRef result = extractPdfOperation.execute(executionContext);

            // Save the result to the specified location.
            result.saveAs("output/ExtractTextInfoFromPDFWithInMemoryAuthCredentials.zip");

        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error("Exception encountered while executing operation", ex);
        }
    }
}
