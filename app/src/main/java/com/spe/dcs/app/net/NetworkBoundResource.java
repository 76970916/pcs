package com.spe.dcs.app.net;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.spe.dcs.app.Resource;
import com.spe.dcs.utility.CommonUtils;

import retrofit2.Response;


/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * <p>
 * You can read more about it in the <a href="https://developer.android.com/arch">Architecture
 * * Guide</a>.
 * * <p>
 * 有关NetworkBoundResource的设计意图可以参考： https://developer.android.com/topic/libraries/architecture/guide.html#addendum
 * <p>
 * Test Case :
 * 1. db success without network
 * 2. db success with network success
 * 3. db success with network failure
 * 4. network success
 * 5. network failure
 *
 * @param <ResultType>
 */
public abstract class NetworkBoundResource<ResultType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource() {
        result.setValue(Resource.loading(null));
        if (shouldFetch(null)) {
            fetchFromNetwork(null);
        } else {
            new AsyncTask<Void, Void, ResultType>() {

                @Override
                protected ResultType doInBackground(Void... voids) {
                    return loadFromDb();
                }

                @Override
                protected void onPostExecute(ResultType entity) {
                    MediatorLiveData<ResultType> mediatorLiveData = new MediatorLiveData<>();
                    mediatorLiveData.setValue(entity);
                    result.addSource(mediatorLiveData, resource -> {
                        result.removeSource(mediatorLiveData);
                        if (resource != null) {
                            result.setValue(Resource.success(resource));
                        } else {
                            result.setValue(Resource.error("查询出错！", null));
                        }
                    });

                }
            }.execute();

        }
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<Resource<ResultType>> netResource = createCall();
        result.addSource(netResource, resource -> {
            result.removeSource(netResource);
            result.setValue(resource);
            saveResultAndReInit(resource);
        });
    }

    @MainThread
    public void saveResultAndReInit(Resource<ResultType> response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                //
                if (response.status.equals(com.spe.dcs.app.net.Status.SUCCESS)&&response.data != null)
                    saveCallResult(response.data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // we specially request a new live data,
                // otherwise we will get immediately last cached value,
                // which may not be updated with latest results received from network.
                //result.addSource(loadFromDb(),newData -> result.setValue(Resource.success(newData)));
            }
        }.execute();
    }

    // returns a LiveData that represents the resource, implemented
    // in the base class.
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

    // Called to insertItem the mResult of the API response into the database
    @WorkerThread
    protected abstract void saveCallResult(@NonNull ResultType item);

    // Called with the data in the database to decide whether it should be
    // fetched from the network.
    @MainThread
    protected boolean shouldFetch(@Nullable ResultType data) {
        return CommonUtils.isNetAvailable();
    }

    // Called to get the cached data from the database
    @NonNull
    @MainThread
    protected abstract ResultType loadFromDb();

    // Called to create the API call.
    @NonNull
    @MainThread
    protected abstract LiveData<Resource<ResultType>> createCall();

    @WorkerThread
    protected ResultType processResponse(Response<ResultType> response) {
        return response.body();
    }

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected void onFetchFailed() {

    }
}