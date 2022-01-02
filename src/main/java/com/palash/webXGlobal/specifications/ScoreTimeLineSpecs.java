package com.palash.webXGlobal.specifications;

import com.palash.webXGlobal.entities.ScoreTimeline;
import org.springframework.data.jpa.domain.Specification;

public class ScoreTimeLineSpecs {

    public static Specification<ScoreTimeline> allScoresByMatch(Long match) {
        return (root, query, cb) -> {
            /*return cb.equal(root.get("area_id"), area_id);*/
            // query.orderBy(cb.desc(root.get("id")));
            //return cb.and(cb.equal(root.get("ttype"),ttype),cb.equal(root.get("area_id"),area_id),cb.between(root.get("tdate"),from,to));
            return cb.and(cb.equal(root.get("matchId").get("id"), match));
        };

    }
}
