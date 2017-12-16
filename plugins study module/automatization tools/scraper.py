import urllib.request as req
import urllib.error as err
import re

abs_url = "https://protegewiki.stanford.edu"
# regex for extracting all text from tags that will contain and plugins url
cells = re.compile("<table[^>]*>(.*)</table>", re.IGNORECASE | re.DOTALL)
# regex for extracting each url
rel_url = re.compile("<td><a\s*href\s*=\s*\"([^\"]*)\"")

# START: PARAMETER TO ADJUST FOR OUR SEARCH
# list of regex for keywords that represent our search criteria
key_words = [re.compile("edit", re.IGNORECASE),
             re.compile("modif", re.IGNORECASE),
             re.compile("ontolog", re.IGNORECASE)]
# list of weights for each keyword (or how important is each keyword)
key_words_weights = [1.2, 1, 0.4]
# END


def check_for_keywords(url):
    resp = req.urlopen(url)

    # area of interest is in tag <div id="mw-con\tent-text" ...>
    # area of interest ends at next sibling of ^, <div id="catlinks" ...>
    content_to_search = (str(resp.read()).split("<div id=\"mw-content-text\"")[1]).split("<div id=\"catlinks\"")
    score = 0
    for pos, i in enumerate(key_words):
        score += len(i.findall(content_to_search[0])) * key_words_weights[pos]
    return url, score


if __name__ == "__main__":
    try:
        resp = req.urlopen(open("url.txt").readline())
        table = cells.findall(str(resp.read()))
        urls = rel_url.findall(table[0])
        search_result = []
        for pos, i in enumerate(urls):
            ret = check_for_keywords(abs_url + i)
            if ret[1] > 0:
                search_result.append(ret)
        print("find {} results".format(len(search_result)))
        search_result.sort(key=lambda x: x[1], reverse=True)
        for i in search_result:
            print(i)
    except err.URLError as ex:
        print("Error at request.", ex)

