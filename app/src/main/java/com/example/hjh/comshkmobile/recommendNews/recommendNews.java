package com.example.hjh.comshkmobile.recommendNews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snu.ids.kkma.ma.MExpression;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class recommendNews {
    private String HNewsURL = "http://www.hani.co.kr/arti/politics/politics_general/list";
    private Document doc = null;
    private org.jsoup.Connection connection = null;
    public List<String> titleList = new ArrayList<String>();
    public List<String> hrefList = new ArrayList<>();
    public List<String> contentList = new ArrayList<>();

    public void calcNewsURL() {
        while (true) {
            try {
                connection = Jsoup.connect(HNewsURL);
                doc = connection.post();
                break;
            } catch (Exception e) {
                continue;
            }
        }
        Elements list_item = doc.select("div.article-area > span > a");
        for (Element item : list_item) {
            String url = "http://www.hani.co.kr" + item.attr("href");
            hrefList.add(url);
        }
        return;
    }

    public void calcNewsContents() {
        calcNewsURL();
        for (int i = 0; i < hrefList.size(); i++) {
            String nowHref = hrefList.get(i);
            while (true) {
                try {
                    connection = Jsoup.connect(nowHref);
                    doc = connection.post();
                    break;
                } catch (Exception e) {
                    continue;
                }
            }
            String newsTitle = doc.select("h4 > span.title").text();
            titleList.add(newsTitle);
            String NewsContents = doc.select("div.text").text().substring(0, 40);
            contentList.add(NewsContents);
        }
        return;
    }

//    public void parseNews() {
//        List<String> wordList = new ArrayList<>();
//        Set<String> wordSet = new HashSet<>();
//        for (int i = 0; i < contentList.size(); i++) {
//            String nowTitle = titleList.get(i);
//            String nowContents = contentList.get(i);
//            String totalContents = nowTitle + nowContents;
//            String parseContents = maTest(totalContents, true);
//            String[] wordArray = parseContents.split(";");
//            for(int j=0; j<)
//        }
//    }

    public static List sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v2).compareTo(v1);
            }

        });
        return list;
    }

    public static String maTest(String Text, Boolean NNG) {

        if (NNG == false) {
            String Result = "";
            String string = Text.replace("“", "").replace("”", "").replace("‘", "").replace("’", "");
            try {
                MorphemeAnalyzer ma = new MorphemeAnalyzer();
                ma.createLogger(null);
                Timer timer = new Timer();
                List<MExpression> ret = ma.analyze(string);
                ret = ma.postProcess(ret);
                ret = ma.leaveJustBest(ret);
                List<String> itemList = new ArrayList<String>();
                List<Sentence> stl = ma.divideToSentences(ret);
                for (int i = 0; i < stl.size(); i++) {
                    Sentence st = stl.get(i);
                    System.out.println("=============================================  " + st.getSentence());
                    for (int j = 0; j < st.size(); j++) {
                        Result += st.get(j).getExp() + ";";
                    }
                }
                ma.closeLogger();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result;
        } else {
            String NNGResult = "";
            String string = Text;
            try {
                MorphemeAnalyzer ma = new MorphemeAnalyzer();
                ma.createLogger(null);
                List<MExpression> ret = ma.analyze(string);


                ret = ma.postProcess(ret);
                ret = ma.leaveJustBest(ret);
                List<String> itemList = new ArrayList<String>();
                List<Sentence> stl = ma.divideToSentences(ret);
                for (int i = 0; i < stl.size(); i++) {
                    Sentence st = stl.get(i);
                    for (int j = 0; j < st.size(); j++) {
                        itemList.add(st.get(j).toString());
                    }
                }
                for (int i = 0; i < itemList.size(); i++) {
                    Pattern pattern = Pattern.compile("[가-힣]{1,20}\\/NNG");
                    Matcher matcher = pattern.matcher(itemList.get(i));
                    while (matcher.find()) {
                        NNGResult += matcher.group();
                    }
                }
                ma.closeLogger();
            } catch (Exception e) {
                e.printStackTrace();
            }
            NNGResult = NNGResult.replace("/NNG", ";");
            return NNGResult;
        }
    }

}
