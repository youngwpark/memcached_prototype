

public enum CommandEnum
{
    GET, SET, ADD, REPLACE, DELETE, OTHER;

    public static CommandEnum toEnum(final String str)
    {
        for (CommandEnum command : CommandEnum.values())
        {
            if (str.equalsIgnoreCase(command.name()))
            {
                return command;
            }
        }
        return CommandEnum.OTHER;
    }
}

