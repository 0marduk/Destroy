package com.petrolpark.destroy.config;

public class DestroyContraptionsConfigs extends DestroyConfigBase {

    public final ConfigGroup centrifuge = group(0, "centrifuge", Comments.centrifuge);
    public final ConfigInt centrifugeCapacity = i(4000, 1000, "centrifugeCapacity", Comments.millibuckets, Comments.centrifugeCapacity);
    public final ConfigInt centrifugeMaxLubricationLevel = i(10, 0, "centrifugeMaxLubricationLevel", Comments.toDisable, Comments.centrifugeMaxLubricationLevel);

    public final ConfigGroup dynamo = group(0, "dynamo", Comments.dynamo);
    public final ConfigBool dynamoBulkCharging = b(true, Comments.dynamoBulkCharging);
    
    
    @Override
    public String getName() {
        return "contraptions";
    };

    private static class Comments {
        static String toDisable = "[0 to disable]";
        static String millibuckets = "[in millibuckets]";

        static String centrifuge = "Centrifuge";
        static String centrifugeCapacity = "How much each of the Centrifuge's Fluid Tanks can store.";
        static String centrifugeMaxLubricationLevel = "The highest level of lubrication Centrifuges can have.";

        static String dynamo = "Dynamo";
        static String dynamoBulkCharging = "Whether Dynamos can charge multiple Item Stacks at once.";
    };
}
