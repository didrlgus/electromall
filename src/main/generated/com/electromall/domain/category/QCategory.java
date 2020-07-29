package com.electromall.domain.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = 1275149247L;

    public static final QCategory category = new QCategory("category");

    public final com.electromall.domain.QBaseTimeEntity _super = new com.electromall.domain.QBaseTimeEntity(this);

    public final StringPath catCd = createString("catCd");

    public final NumberPath<Integer> catLv = createNumber("catLv", Integer.class);

    public final StringPath catNm = createString("catNm");

    public final StringPath cnntUrl = createString("cnntUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath upprCatCd = createString("upprCatCd");

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

