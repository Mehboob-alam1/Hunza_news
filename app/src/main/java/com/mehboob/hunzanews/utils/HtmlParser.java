package com.mehboob.hunzanews.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

    public static String parseHtml(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);

        // Remove style attributes
        Elements elementsWithStyle = document.select("[style]");
        for (Element element : elementsWithStyle) {
            element.removeAttr("style");
        }

        // Remove all tags, leaving only the text
        return document.text();
    }
}
