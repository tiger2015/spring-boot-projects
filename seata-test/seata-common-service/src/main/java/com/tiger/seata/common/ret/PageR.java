package com.tiger.seata.common.ret;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 15:33
 * @Description
 * @Version: 1.0
 **/
@Data
public class PageR<T> implements Serializable {
    private static final long serialVersionUID = -322670020534451663L;
    private long total;
    private int pageSize;
    private int pageNum;
    private int totalPage;
    private List<T> list;
}
