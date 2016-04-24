package zakp.fireproject.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hannesdorfmann.sqlbrite.dao.Dao;

import java.util.List;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import zakp.fireproject.data.model.BaseModel;


public abstract class BaseDao<T extends BaseModel> extends Dao {

    protected String[] columns;
    protected String tableName;

    public BaseDao(){
        columns = getColumns();
        tableName = getTableName();
    }

    @Override
    public void createTable(SQLiteDatabase database) {

        CREATE_TABLE(tableName,
                getColumnsToCreate())
                .execute(database);

    }

    protected abstract String[] getColumnsToCreate();

    protected abstract String[] getColumns();

    protected abstract String getTableName();

    protected abstract Func1<Cursor, T> getMapper();

    public Observable<T> saveAll(List<T> list) {
        return Observable.create(subscriber -> {
            try {
                for (T object : list) {
                    Observable<Long> insertObservable = save(object);

                    //for now empty subscriber, only for purpose of consuming Rx stream.
                    insertObservable.subscribe(new Subscriber<Long>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Long aLong) {

                        }
                    });

                    subscriber.onNext(object);
                }

            } catch (Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }

    public Observable<Long> save(T object) {
        return insert(tableName, object.provideContentValues(), SQLiteDatabase.CONFLICT_REPLACE);

    }

    public Observable<List<T>> getAll() {
        return query(SELECT(columns).FROM(tableName)).run().mapToList(getMapper());

    }
}
