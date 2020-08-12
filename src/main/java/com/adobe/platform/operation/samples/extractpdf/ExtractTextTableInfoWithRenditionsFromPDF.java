/*
 * Copyright 2020 Adobe
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute this file in
 * accordance with the terms of the Adobe license agreement accompanying
 * it.
 */

package com.adobe.platform.operation.samples.extractpdf;

import com.adobe.platform.operation.ExecutionContext;
import com.adobe.platform.operation.auth.Credentials;
import com.adobe.platform.operation.exception.SdkException;
import com.adobe.platform.operation.exception.ServiceApiException;
import com.adobe.platform.operation.exception.ServiceUsageException;
import com.adobe.platform.operation.io.FileRef;
import com.adobe.platform.operation.pdfops.ExtractPDFOperation;
import com.adobe.platform.operation.pdfops.constants.PDFElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * This sample illustrates how to extract Text, Table Elements Information from PDF along with renditions of Table
 * elements.
 * <p>
 * Refer to README.md for instructions on how to run the samples & understand output zip file.
 */
public class ExtractTextTableInfoWithRenditionsFromPDF {

    // Initialize the logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractTextTableInfoWithRenditionsFromPDF.class);

    public static void main(String[] args) {

        try {

            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .fromFile("pdftools-api-credentials.json")
                    .build();

            //Create an ExecutionContext using credentials and create a new operation instance.
            ExecutionContext executionContext = ExecutionContext.create(credentials);
            ExtractPDFOperation extractPdfOperation = ExtractPDFOperation.createNew();

            // Set operation input from a source file.
            FileRef source = FileRef.createFromLocalFile("src/main/resources/extractPdfInput.pdf");
            extractPdfOperation.setInputFile(source);
            extractPdfOperation.addElementsToExtract(Arrays.asList(PDFElementType.TEXT, PDFElementType.TABLES));
            extractPdfOperation.addElementToExtractRenditions(PDFElementType.TABLES);

            // Execute the operation.
            FileRef result = extractPdfOperation.execute(executionContext);

            // Save the result to the specified location.
            result.saveAs("output/ExtractTextTableInfoWithRenditionsFromPDF.zip");

        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error("Exception encountered while executing operation", ex);
        }
    }
}
