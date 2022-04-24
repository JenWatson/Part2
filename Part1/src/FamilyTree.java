public class FamilyTree {

    private class FamilyTreeNode {
        private String name;
        private FamilyTreeNode partner;
        private FamilyTreeNode firstChild;
        private FamilyTreeNode nextSibling;

    }

    public class UniqueConstraintException extends Exception {
    }

    private FamilyTreeNode ancestor;

    public FamilyTree(String ancestorName, String partnerName) {
        this.ancestor = new FamilyTreeNode();
        this.ancestor.name = ancestorName;

        FamilyTreeNode partner = new FamilyTreeNode();
        partner.name = partnerName;

        partner.partner = this.ancestor;
        this.ancestor.partner = partner;
    }

    public void addChild(String childName) throws UniqueConstraintException {
        FamilyTreeNode child = new FamilyTreeNode();
        child.name = childName;
        if (this.ancestor.firstChild == null) {
            this.ancestor.firstChild = child;
            this.ancestor.partner.firstChild = child;
        } else {
            FamilyTreeNode next = this.ancestor.firstChild;
            //Check for unique
            if (next.name.equalsIgnoreCase(childName)) {
                throw new UniqueConstraintException();
            }
            while (next.nextSibling != null) {
                // check unique
                if (next.nextSibling.name.equalsIgnoreCase(childName)) {
                    throw new UniqueConstraintException();
                }
                next = next.nextSibling;
            }
            next.nextSibling = child;
        }
    }
    private String getFamilyDetails(FamilyTreeNode familyNode) {
        String familyTreeDetails = new String();
        familyTreeDetails += familyNode.name + " partner " + familyNode.partner.name + "\n";
        FamilyTreeNode family = familyNode.firstChild;
        if (family == null) {
            familyTreeDetails += " has no children\n";
        } else {
            while (family != null) {
                familyTreeDetails += " " + family.name + "\n";
                family = family.nextSibling;
            }
        }
        return familyTreeDetails;
    }
    public String toString() {
        String familyTreeDetails = new String();
        familyTreeDetails += getFamilyDetails(this.ancestor);
        familyTreeDetails += getFamilyDetails(this.ancestor.partner);

        return familyTreeDetails;
        }
    }