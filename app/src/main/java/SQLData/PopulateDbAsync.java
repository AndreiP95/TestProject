package SQLData;

import android.os.AsyncTask;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private DataAccessWord mDao;

    public void setmDao(DataAccessWord mDao) {
        this.mDao = mDao;
    }

    public DataAccessWord getmDao() {
        return mDao;
    }

    PopulateDbAsync() {
    }

    PopulateDbAsync(WordRoomDatabase db) {
        mDao = db.wordDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Word word = new Word("Hello");
        mDao.insert(word);
        word = new Word("World");
        mDao.insert(word);
        return null;
    }
}