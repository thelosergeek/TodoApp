# TodoApp
Todo App is a simple android app based on MVVM architecture.

New Note Screen | Main Screen
------------ | -------------
![](https://i.ibb.co/YXMhh8t/newapp.png) | ![](https://i.ibb.co/F3fS4fX/newapp2.png)



## Architecture Used

<img src="https://user-images.githubusercontent.com/15268903/80802176-70ca8380-8bd0-11ea-8a4d-770895f2b9ee.png" alt="https://developer.android.com/jetpack/docs/guide" style="width:200;height:200">




### Install the apk

[Download App](https://drive.google.com/drive/u/0/folders/1FGLctXqljXBiMcQmRqd5Yvh6T5uonUQT)


### Directory Structure

The following is a high level overview of relevant files and folders.

```
└───Todo App
    └───app
        └───src
            └───main
                └───java
                    └───in.thelosergeek.in
                                ├───data
                                │   ├───database
                                │   │   │   NoteDao.kt
                                │   │   │   NoteDatabase.kt
                                │   │
                                │   ├───model
                                │   │   │   Note.kt
                                │   │
                                │   └───repository
                                │           NoteRepository.kt
                                │
                                ├───adapter
                                │   │   NoteItem.kt
                                ├───ui
                                │   |   MainActivity.kt
                                │   │   NewNoteActivity.kt
                                │
                                └───utility
                                │   ├───SwipeTouchCallBack.kt 
                                │   
                                │   
                                ├───viewmodel  
                                │   ├───NoteViewModel.kt                        
```
Demo

![](https://media.giphy.com/media/AQ1j9jxXPKpGeb2eHQ/giphy.gif)
