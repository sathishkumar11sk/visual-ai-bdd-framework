package com.visualai.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebsiteInputResolver {

    private static final Logger logger =
            LoggerFactory.getLogger(WebsiteInputResolver.class);

    private static final String SYSTEM_PROPERTY_KEY = "website";

    private WebsiteInputResolver() {}

    // =============================

    public static String resolve() {

        logger.info("Resolving target website from Maven property (-Dwebsite)");

        String url = System.getProperty(SYSTEM_PROPERTY_KEY);

        if (url == null || url.trim().isEmpty()) {

            logger.error("No website provided via -Dwebsite");

            throw new IllegalStateException(
                    "Provide website using: mvn clean test -Dwebsite=example.com"
            );
        }

        String normalized = normalizeUrl(url.trim());

        logger.info("Resolved URL: {}", normalized);

        return normalized;
    }

    // =============================

    private static String normalizeUrl(String rawUrl) {

        if (rawUrl.startsWith("http://") || rawUrl.startsWith("https://")) {
            return rawUrl;
        }

        return "https://" + rawUrl;
    }
}