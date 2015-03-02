/**
 * 
 */
package textrank.tagger;

import java.util.List;

import com.huaban.analysis.jieba.JiebaSegmenter;

/**
 * @author zhangcheng
 *
 */
public class JiebaTagger implements Tagger {

	private JiebaSegmenter segmenter;
	
	public JiebaTagger() {
		segmenter = new JiebaSegmenter();
	}
	
	@Override
	public List<Term> seg(String text) {
		return null;
	}
}
