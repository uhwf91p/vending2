package com.example.order.app.domain.usecase

import android.content.ContentValues.TAG
import android.util.Log
import com.example.order.core.App
import com.example.order.core.GlobalConstAndVars
import com.example.order.app.domain.model.ServerResponseDataFireBase
import com.example.order.repository.LocalRepository
import com.example.order.repository.LocalRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class FirebaseCaseImpl:FireBaseCase {
    private val database:LocalRepository=LocalRepositoryImpl(App.get1CDAO())
    private val converters:Converters= Converters()

    private val remoteDB = FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
    }


   override fun executeGettingDataFromFirebase(collectionsName:String):MutableList<ServerResponseDataFireBase>{
        var listFromCloud:MutableList<ServerResponseDataFireBase> = mutableListOf()
        remoteDB.collection(collectionsName)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    database.insertToDB(converters.mapToDB(converters.mapDocumentToRemoteTask(document)))
                    listFromCloud.add(converters.mapDocumentToRemoteTask(document))
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return listFromCloud
    }

    fun saveList (list:MutableList<ServerResponseDataFireBase>){
        GlobalConstAndVars.listFromCloud=list
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
/*fun getAllTask(): Single<List<com.example.order.datasource.fireBase.Task>> {
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

}*/

/*
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
*/






