/**
 * 
 */
package textrank.tagger;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * @author zhangcheng
 *
 */
public class StanfordTagger implements Tagger {

	private CRFClassifier<CoreLabel> segmenter;

	/**
	 * 
	 */
	public StanfordTagger(String basedir) {

		Properties props = new Properties();
		props.setProperty("sighanCorporaDict", basedir);
		// props.setProperty("NormalizationTable", "data/norm.simp.utf8");
		// props.setProperty("normTableEncoding", "UTF-8");
		// below is needed because CTBSegDocumentIteratorFactory accesses it
		props.setProperty("serDictionary", basedir + "/dict-chris6.ser.gz" + "," +  basedir + "/dict-chris6.ser.gz");
		props.setProperty("inputEncoding", "UTF-8");
		props.setProperty("sighanPostProcessing", "true");

		segmenter = new CRFClassifier<CoreLabel>(props);
		segmenter.loadClassifierNoExceptions(basedir + "/ctb.gz", props);

	@Override
	public List<String> seg(String text) {
		return segmenter.segmentString();
	}

}
