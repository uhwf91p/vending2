package com.example.order.datasource.fireBase

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.order.app.domain.model.ListItem
import com.example.order.core.GlobalConstAndVars
import com.example.order.datasource.Server.ServerResponseData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CloudFireStore {

    companion object {
        private const val TASKS_COLLECTION = "test"
        private const val TASK_FIELD_TITLE = "title"
        private const val TASK_FIELD_CREATED = "created"
    }
    private val remoteDB = FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
    }
    private fun mapDocumentToRemoteTask(document: DocumentSnapshot) = document.toObject(ServerResponseDataFireBase::class.java)!!.apply { id = document.id }
    private fun mapToTask(remoteTask: ServerResponseDataFireBase): com.example.order.datasource.fireBase.Task {
        return Task(

            remoteTask.variant1,
            remoteTask.variant2,
            remoteTask.variant3,
            remoteTask.question,
            remoteTask.picture,
            remoteTask.answer
        )
    }

   fun getAllTask(): Single<List<com.example.order.datasource.fireBase.Task>> {
       var x: List<com.example.order.datasource.fireBase.Task> = listOf()
        return Single.create<List<DocumentSnapshot>> { emitter ->
            remoteDB.collection(TASKS_COLLECTION)
                .get()
                .addOnSuccessListener {
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(it.documents)
                    }
                }
                .addOnFailureListener {
                    if (!emitter.isDisposed) {
                        emitter.onError(it)
                    }
                }
        }
            .observeOn(Schedulers.io())
            .flatMapObservable { Observable.fromIterable(it) }
            .map(::mapDocumentToRemoteTask)
            .map(::mapToTask)
            .toList()

    }

  /*  var x: List<com.example.order.datasource.fireBase.Task> = listOf()
    return Single.create<List<DocumentSnapshot>> { emitter ->
        remoteDB.collection(TASKS_COLLECTION)
            .get()
            .addOnSuccessListener {
                if (!emitter.isDisposed) {
                    emitter.onSuccess(it.documents)
                }
            }
            .addOnFailureListener {
                if (!emitter.isDisposed) {
                    emitter.onError(it)
                }
            }
    }
    .observeOn(Schedulers.io())
    .flatMapObservable { Observable.fromIterable(it) }
    .map(::mapDocumentToRemoteTask)
    .map(::mapToTask)
    .toList()*/



    fun getAllDataFromCollectionCloudFireStore(){
        var listFromCloud:MutableList<ServerResponseDataFireBase> = mutableListOf()


        remoteDB.collection("test")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    listFromCloud.add(mapDocumentToRemoteTask(document))


                    GlobalConstAndVars.listFromCloud=listFromCloud

                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
       /* var conv:ServerResponseDataFireBase?
        var list:MutableList<ServerResponseDataFireBase?> = mutableListOf()
        var taskList:List<DocumentSnapshot> = listOf()
        var mes:String

        var x1=0
        var x=0
        remoteDB.collection("test")
        .get()
            .addOnSuccessListener { querySnapshot ->
                taskList= querySnapshot.documents
                GlobalConstAndVars.taskList=taskList


                // Успешно получили данные. Список в querySnapshot.documents
            }



            .addOnFailureListener { exception ->
               mes= exception.message.toString()


                // Произошла ошибка при получении данных
            }
        for (documentSnapshot in taskList) {
           documentSnapshot.toObject(ServerResponseDataFireBase::class.java)


        }
*/
    }

    fun getDataByFieldAndValue(){
        remoteDB.collection("ТестыПДД")
        .whereEqualTo("title", "Task1")
            .get()
            .addOnSuccessListener { querySnapshot ->
                // Успешно получили данные. Список в querySnapshot.documents
            }
            .addOnFailureListener { exception ->
                // Произошла ошибка при получении данных
            }
    }
    }






