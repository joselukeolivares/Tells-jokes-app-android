package com.example.bigger.javajokeslib;

import java.util.Random;

public class JavaJokes{

    final String [] jokes={
        "Why did the Python programmer guy got rejected by a Java programmer girl? Because he was not her type.",
        "Why do java programmers wear glasses? Because they can’t C#",
        "Existential Java I mixed de-caf coffee with regular coffee and they must have cancelled each other out because now I feel nothing.",
        "Why do blind programmers use Java? Because they can't C.",
        "My wife is the least technical person in the world, so when she told me she had finished installing Java, I was astounded. Until she held up her empty coffee cup.",
        "Why should u buy a WiFi enabled espresso machine? To get the latest Java updates",
        "My son finally landed a position as a software engineer. He proudly told me that his new job title will be “Java Developer.”        I didn’t have the heart to tell him that means he’ll be making the coffee.",
        "Why are Communists bad Java programmers? They don't like classes."
    };
    
public String getJavaJokes(){
    int index=new Random().nextInt(jokes.length);
    return jokes[index];
} 
}