/*
* This program searches USPTO index using a year doc vector from DBLP
* 1. Extracts the TF.IDF vector from DBLP index for the given year
* 2. Terms from this document vector are boostrapped into a query using Term Boost
* 3. Searches through the USPTO index and return the list of relevant patents
*
*/

// FRAGMENT TO EXTRACT TF.IDF

public void getTfidf(IndexReader reader, Writer out, String field) throws IOException {

    Bits liveDocs = MultiFields.getLiveDocs(reader);
    TermsEnum termEnum = MultiFields.getTerms(reader, field).iterator(null);
    BytesRef term = null;
    TFIDFSimilarity tfidfSim = new DefaultSimilarity();
    int docCount = reader.numDocs();

    while ((term = termEnum.next()) != null) {
      Term termInstance = new Term(field, term);
      long indexDf = reader.docFreq(termInstance);

      DocsEnum docs = termEnum.docs(reader.getLiveDocs())
      while(docs.next() != DocsEnum.NO_MORE_DOCS) {
          double tfidf = tfidfSim.tf(docs.freq()) * tfidfSim.idf(docCount, indexDf);
          // store it


// FRAGMENT TO GENERATE QUERY

TermQuery tq1 = new TermQuery(new Term("text", "term1"));
tq1.setBoost(5f);
TermQuery tq2 = new TermQuery(new Term("text", "term2"));
tq2.setBoost(0.8f);
BooleanQuery query = new BooleanQuery();
query.add(tq1, Occur.SHOULD);
query.add(tq2, Occur.SHOULD);
