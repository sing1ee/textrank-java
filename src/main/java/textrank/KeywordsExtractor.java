/**
 * 
 */
package textrank;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import textrank.pagerank.Node;
import textrank.pagerank.UndirectWeightedGraph;
import textrank.tagger.JiebaTagger;
import textrank.tagger.Term;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author zhangcheng
 * 
 */
public class KeywordsExtractor {


    public static void main(String[] args) throws Exception {

        Set<String> filt = Sets.newHashSet();
        filt.add("n");
        filt.add("v");
        filt.add("nt");
        filt.add("nz");

        String text = "国家统计局服务业调查中心高级统计师赵庆河表示，PMI小幅回升的主要原因，一是春节过后，企业集中开工，生产活动有所加快；二是在近期一系列稳增长、调结构、促改革的政策措施利好作用下，企业对未来一段时间的市场预期持乐观态度的比例有所增加；三是近期原油等大宗商品价格降幅收窄，一些建筑钢材产品价格出现回升，市场环境的有利因素对企业的生产也带来一些积极影响。";
        // 1.build text graph

        // 1.1 get tokens
        List<Term> terms = new JiebaTagger().seg(text);

        // 1.2 filt pos
        Term[] filtTerms = Lists.newArrayList(terms.stream().filter(t -> {
            return filt.contains(t.getPos());
        }).iterator()).toArray(new Term[0]);

        // 1.3 build graph
        CounterMap cm = new CounterMap();

        int span = 5;

        for (int i = 0; i < filtTerms.length; ++i ) {
            for (int j = i + 1; j < i + span && j < filtTerms.length; ++j) {
                cm.incr(filtTerms[i].getText() + ":" + filtTerms[j].getText());
            }
        }
        // TODO we can do more optimization as follow: 1) pos 2) stopwords 3) rational span
        UndirectWeightedGraph uwg = new UndirectWeightedGraph();
        for (String pair : cm.countAll().keySet()) {
//			System.out.println(pair + "\t" + cm.get(pair));
            String[] segs = Lists.newArrayList(Splitter.on(":").split(pair)).toArray(new String[0]);
            uwg.addNode(segs[0], segs[1], cm.get(pair));
        }
        // 2. rank
        uwg.rank();
        List<Node> list = uwg.topk(10);
        list.forEach(t -> {
            System.out.println(t.label + "\t" + t.rank);
        });
    }
}
