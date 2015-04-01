/**
 * 
 */
package textrank.tagger;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

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

		return Lists.transform(segmenter.process(text, JiebaSegmenter.SegMode.INDEX), new Function<SegToken, Term>() {
			@Override
			public Term apply(SegToken input) {
				Term t = new Term();
				t.setText(input.word.getToken());
				t.setPos(input.word.getTokenType());
				return t;
			}
		});
	}
}
