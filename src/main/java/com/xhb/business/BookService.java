package com.xhb.business;

import com.xhb.prism.http.ResultConverterFactory;
import com.xhb.prism.http.annotation.*;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

@BaseUri("https://coursaction.talcloud.com/")
public interface BookService {

    static class Result extends ResultConverterFactory.ResultInfo<Result>
    {
        int code;
        String msg;
        Object data;

        @Override
        public Result newResult() {
            return new Result();
        }

        @Override
        public void setStatus(Result result, int status) {
            result.code = status;
        }

        @Override
        public void setMessage(Result result, String message) {
            result.msg = message;
        }

        @Override
        public void setData(Result result, Object data) {
            result.data = data;
        }

        @Override
        public void check(Result result) {
            if (result.code != 0)
                throw new RuntimeException(result.msg);
        }

        @Override
        public Object data(Result result) {
            return result.data;
        }
    }

    static class DataElement
    {
        public String pageId;
        public String paperUrl;
        public List<String> areaInfo;
    }
    static class PaperTypesetInfo
    {
        public String paperId;
        public List<DataElement> datas;
    }
    static class PaperData
    {
        public PaperTypesetInfo paperTypesetInfo;
    }

    @GET("preparation/app/v2/teaching/paper/typeset/info")
    Observable<PaperData> getPaperInfo(@Query("bookId") String bookId, @Query("chapterId") String chapterId);
}
