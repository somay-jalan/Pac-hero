<h1>PacHero </h1>

- **Team**
    -
    - Somay Jalan : 2022505
    - Pankhuri Singh : 2022348

- **<h3>Description</h3>**
    - 
    
    PacHero is an implementation of the classic game StickHero but with a twist- Your main character is good old Pacman stuck in space. To save himself and go home PacHero must navigate and cross the void between futuristic pillars in space by extending a his only weapon left - A stick that can be extended infinitely. At the center of the stick is a lightsaber. When he lands on the lightsaber ,he gets extra points. For sustainance in space, he will also be able to collect cherries.

    (*Bonus*) But this won't be easy. Additonally when PacHero manages to land on a stick accurately , his age old enemy: Ghost Monster will come and chase him. He needs to accurately dodge him to continue his journey. 

    If PacHero is happy and healthy after consuming a set amount of cherries he can also change his costumes !

- **<h3>Rules</h3>**
    - 

    - Do not let PacHero die
    - Dodge the Ghosts
    - Collect Cherries
    - Extra points for landing accurately
    - That's all :) 

- **<h3>Game Logic</h3>**
    - 

    - *Design Patterns*:

        The game makes use of 2 design patterns from the Gang of Four : Singelton and Template.

        - **Singleton**: 
        
            Implemented while running the Background music of the game. We want it to be running the entire duration of the game , Hence a thread is implemntated that runs parallely to the main code. Since we want this thread to be created just once throughtout the game , a singelton pattern is implemented here.

        - **Template:**

            To make the game interactive and appealing we've added sounds for an enhanced User experience. To facilitate this feature,without having to make sound threads repeatedly we've implemneted the template design pattern since the code is mostly the same.

    - *Thread Usage:*

        The game makes use of 7 different runnable class threads (and one main thread ofcourse)

        - **Start Page Loading Animation:**

            An animation of loading dots has been added when the game launches. A thread has been added here since we want it to run indefinitely. 

        - **Music:**

            To run all the sounds (6 in total), threads need to be implemnted since we want them to run  in the background and not halter the working for our main code.

    - *Bonus Code Implementation*

        - **Shop Page:**

            PacHero can change his attire ( somewhat like the original game). New hats can be bought in exchange for a certain amount of cherries. Once bought he can switch between costumes as per his wish. 

        - **Ghost Monster**:

            To make things more challenging and engaging whenever the stick lands perfectly in between the pillar ,on the lightsaber (and the space between pillars is ample) A Ghost monster appears. To make it out alive PacHero needs to succesfully dodge it by flipping over to the other side of the stick.

        - **Perfect and Highscore Notification :** 

            Whenever PacHero lands on the lightsaber or the user achieves the Highest score of theirs, A custom notifcation appears at the topics announcing their acheivement 

    - *Assumptions:*
        - Pachero has 3 extra lives, He can avail them in exchange of some cherries, for each successive life he needs an increased amount of cherries. 

        - Whenever our hero collects a cherry, that will get added to his total count regardless of if he completes crossing that void or not.

        - Because of logical constrains, The ghost will only appear if the stick lands exactly at the middle of the pillar *and* If the distance between pillars is significant enough to allow him to flip to the other side.

        - **Important** : The game cannot be completely free of errors because we may not have handled some rare custom exceptions or because of javaFx glitches. We apologize if this happens and some part of the game stops running. *Hence we request you to re-run the code in this situation as it will work that way.*










