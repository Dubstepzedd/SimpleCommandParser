# SimpleCommandParser
## NOTE: The Command Parser is still a work in progress

This is a simple command parser who's purpose is to take a `String` and interpret it. If the `String` contains the correct format for a specific command, it will execute that specific command with the arguments given by the user.

## How to use

The Parser is the core class of the command parser.

You initialise a Parser object by doing `Parser parser = new Parser(separator)` where ``separator`` is a `String` that tells the Parser how to split the `String`.

Before parsing a `String` you need to add all the commands that will be used. These commands have to inherit from the `Command` class, but you can also create an `anonymous class` of`Command`. e.g: `new Command() { ... }`. 

The `Command` class has a few methods. 

The most important ones are `execute(Object[] args)`, `getArguments()` and `getKeyWord()`.
`execute(Object[] args)` is the method that will be run if the command is executed. The parameter args is an array of `Object` that contain the argument data. 
`getArguments()` lets you define the command's arguments. You can either set an argument to the type `TYPE.STRING`,`TYPE.INTEGER`,`TYPE.BOOLEAN` or `TYPE.FLOAT`(See the Argument class for more information). If the command that is run does not match these types, an error complaining on the syntax will be thrown for the user to see. 
`getKeyWord()` lets you define the "call command". e.g the first string after the separator. ( `/spawn has the key word "spawn"` )

You add a command by calling `.register(command)` on the `Parser` class instance where command is a legitimate command.

When you have added your commands you are ready to parse!

To parse a `String` you only need to call `.parse(String str)` on the `Parser` instance where `String str` is the input. e.g `"help stopcommand"`.



