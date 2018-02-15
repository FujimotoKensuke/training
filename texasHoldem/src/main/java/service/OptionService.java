package service;

import javax.enterprise.context.RequestScoped;

/**
 * コメント追加のインターフェースです。
 */
@RequestScoped
public interface OptionService {
    
    /**
     * コメントをDBのユーザーテーブルに追加します。
     */
    public void execute();
}
