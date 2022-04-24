public class FamilyTreeTest {
    public static void main(String[] args) throws FamilyTree.UniqueConstraintException {
        String name = Input.getString("Enter ancestor name: ");
        String partnerName = Input.getString("Enter partner's name: ");
        FamilyTree familyTree = new FamilyTree(name, partnerName);
        Integer option;
        do {
            System.out.println("0: quit");
            System.out.println("1: add child");
            System.out.println("2: display family");
            option = Input.getInteger("input option: ");
            switch (option) {
                case 0:
                    System.out.println("Quitting.....");
                    break;
                case 1:
                    name = Input.getString("Input the child's name: ");
                    familyTree.addChild(name);
                    break;
                case 2:
                    System.out.println(familyTree);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        } while (option != 0);
    }
}
