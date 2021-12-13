
# <ins>SimpleCommandParser</ins>

### <ins>Short Description
This is a simple Command Parser. It allows the user to define commands and to execute them with help of the ``Parser`` class's ``.parse()`` method.

## <ins>How to use the Command Parser</ins>

### <ins>Creating commands</ins>
In order to use the Command Parser, you first need to create your commands. These commands consist of two components, a ``literal`` and one or more ``arguments``.

The command is built upon a ``Root``. The Root's constructor takes one parameter, a ``String``. This ``String`` is the literal of the command. That being the command identifier. 

Initialize a ``Root`` like this: ``Root root = new Root(literal)``.

Now we have to add arguments to the ``Root``. This is done through the ``.then(ArgumentNode)`` method. 
An ``ArgumentNode`` takes a ``TYPE`` as a parameter. ``TYPE`` is a part of the ``Token`` class. 
``TYPE`` makes defining a data type for an argument possible. For example do: ``ArgumentNode node = new ArgumentNode(Token.TYPE.STRING)`` to initialize an argument with the data type ``String``.

With this now covered, you can add an argument to the ``Root`` by doing: ``root.then(new ArgumentNode(Token.TYPE.STRING))``. You can of course alter the data type. 

An ``ArgumentNode`` also supports the ``.then(ArgumentNode)`` method. You can use this to add more arguments to the root. You might want to have a help command with the following
syntax: ``help STRING INTEGER``. If that is the case you will have to use the ``.then(ArgumentNode)`` on  the previously added ``ArgumentNode``. This pattern repeats itself. 

The last step is to add a behaviour (the code that is run when the node is executed) to the arguments. This is done through the ``.executes(Command)``. 

A command is a functional interface with one method, namely ``execute(Object[] args)``. It is therefore possible to insert a `lambda` as a parameter in the ``.executes(Command)`` method.

An example:
```Root root = new Root("help").then(new ArgumentNode(Token.TYPE.STRING).executes( (args) -> System.out.prinln(args)}))```

It is also possible to use the ``.executes(Command)`` method on the ``Root`` itself. That command will, however, only be executed when the user inputs no arguments.

Now we need to add the newly added ```Root``` to the ``Dispatcher`` class. The ``Dispatcher`` is only a class that contains all the commands / Roots.
Initialize the ``Dispatcher`` using the following code snippet: ``Dispatcher dispatcher = new Dispatcher()``.

Add a ``Root`` by utilizing the ``.register(Root)`` method. 

### <ins>Parsing user input</ins>

The first step is to initialize a ``Parser``.

``Parser parser = new Parser(String)``. The ``String`` parameter is the separator. For example, if you have ``String str = "help my cat"``, and the separator is ``" "``, it will
split the ``String`` on all the spaces.

Now we need to parse some kind of input. This is done through the ``.parse(String, Dispatcher)`` method. Simply input a ``String`` that matches a command and an instance of the ``Dispatcher`` class.

### <ins>Exceptions</ins>
There are 3 types of exceptions. ``CommandCreationError``, ``CommandSyntaxError`` and ``ExecutionCommandError``.
Two of these are related to the creation of the commands, while one is thrown when the input of the user is faulty.

``CommandCreationError`` is thrown when something goes wrong with the creation of a command. One example would be if you have multiple
``executes(Command)``. 

``ExecutionCommandError`` is also related to the creation of a command, but is separate as you might want to handle this differently.
This is thrown when an argument node does not have a ``executed(Command)`` when the ``Parser`` is trying to execute that node.
This will only occur if the user tries to run a command such as ``help 15`` but you only have a ``executes(Command)`` on the ``Root`` and the last ``ArgumentNode``.

``CommandSyntaxError`` is thrown when the user's input does not match any of your commands.

### <ins>Conclusion</ins> 
This Command Parser is not perfect, and might not suit everyone's needs. If you want a more advanced command parser I would recommend using Mojang's ``Brigadier`` library which is 
available here on GitHub. The syntax of this Command Parser has taken inspiration from their library, so much credit to the ``Brigadier`` team!

I hope you find the command parser useful!
