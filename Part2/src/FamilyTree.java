import java.util.LinkedList;

public class FamilyTree {

    private int uniqueIdentifier = 1;
    private FamilyTreeNode ancestor;
    private LinkedList <FamilyTreeNode> familyList = new LinkedList();

    private class FamilyTreeNode {
        private int identifier;
        private String name;
        private FamilyTreeNode partner;
        private FamilyTreeNode firstChild;
        private FamilyTreeNode nextSibling;
        public FamilyTreeNode() {
            this.identifier = uniqueIdentifier;
        }
        public int getIdentifier() {
            return identifier;
        }
    }

    public class UniqueConstraintException extends Exception { }
    public class FamilyNotFoundException extends Exception { }
    public class PartnerNeededException extends Exception { }
    public class FamilyHasPartnerException extends Exception { }

    public FamilyTree(String ancestorName) {
        this.ancestor = new FamilyTreeNode();
        this.ancestor.name = ancestorName;
        familyList.add(ancestor);
        UniqueIdentifier++;
    }

    public void addPartner (String partnerName, familyId) throws FamilyNotFoundException, FamilyHasPartnerException {
        FamilyTreeNode family = findFamily(familyID);
        if (family == null) {
            throw new FamilyNotFoundException();
        }
        if (family.partner != null) {
            throw new FamilyHasPartnerException();
        } else {
            FamilyTreeNode partner = new FamilyTreeNode();
            partner.name = partnerName;
            partner.partner = family;
            family.partner = partner;
            familyList.add(partner);
            uniqueIdentifier++;
        }
    }

    public void addChild(String childName) throws UniqueConstraintException, PartnerNeededException {
        FamilyTreeNode child = new FamilyTreeNode();
        child.name = childName;
        if (this.ancestor.partner == null) {
            throw new PartnerNeededException();
        } else {
            if (this.ancestor.firstChild = null) {
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
        familyList.add(child);
        uniqueIdentifier++;
    }
    public String toString() {
        String familyTreeDetails = new String();
        if(this.ancestor.partner == null) {
            familyTreeDetails += this.ancestor.name + "(identifier " + this.ancestor.identifier + ") has no partner\n";
        } else {
            familyTreeDetails += this.ancestor.name + "(identifier " + this.ancestor.identifier + ") partner " +
                    this.ancestor.partner.name + "(identifier " + this.ancestor.partner.identifier +")\n";
        }
        FamilyTreeNode family = this.ancestor.firstChild;
        if (family == null) {
            familyTreeDetails += " has no children\n";
        } else {
            while (family != null) {
                if(family.partner == null) {
                    familyTreeDetails += " " + family.name + "(identifier " + family.identifier + ") has no partner\n";
                } else {
                    familyTreeDetails += " " + family.name + "(identifier " + family.identifier + ") partner " +
                            family.partner.name + "(identifier " + family.partner.identifier + ")\n";
                    familyTreeDetails += getLegacyDetails(family);
                }
                family = family.nextSibling;
            }
        }
        return familyTreeDetails;
    }
    private String getLegacyDetails(FamilyTreeNode family) {
        String lagacyDetails = new String();
        family = family.firstChild;
        if (family == null) {
            lagacyDetails += " has no children\n";
        } else {
            while (family != null) {
                if (family.partner == null) {
                    lagacyDetails += " " + family.name + "(identifier " + family.identifier + ") has no partner\n";
                } else {
                    lagacyDetails += " " + family.name + "(identifier " + family.identifier + ") partner " +
                            family.partner.name + "(identifier " + family.partner.identifier + ")\n";
                    lagacyDetails += getLegacyDetails(family);
                }
                family = family.nextSibling;
            }
        }
        return lagacyDetails;
    }

}