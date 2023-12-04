/**
 * Autogenerated by Thrift Compiler (0.18.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package io.github.donnie4w.tim.stub;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.18.1)", date = "2023-11-21")
public class TimMessageList implements org.apache.thrift.TBase<TimMessageList, TimMessageList._Fields>, java.io.Serializable, Cloneable, Comparable<TimMessageList> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TimMessageList");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField MESSAGE_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("messageList", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TimMessageListStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TimMessageListTupleSchemeFactory();

  public long id; // optional
  public @org.apache.thrift.annotation.Nullable java.util.List<TimMessage> messageList; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    MESSAGE_LIST((short)2, "messageList");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // MESSAGE_LIST
          return MESSAGE_LIST;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ID,_Fields.MESSAGE_LIST};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MESSAGE_LIST, new org.apache.thrift.meta_data.FieldMetaData("messageList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TimMessage.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TimMessageList.class, metaDataMap);
  }

  public TimMessageList() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TimMessageList(TimMessageList other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetMessageList()) {
      java.util.List<TimMessage> __this__messageList = new java.util.ArrayList<TimMessage>(other.messageList.size());
      for (TimMessage other_element : other.messageList) {
        __this__messageList.add(new TimMessage(other_element));
      }
      this.messageList = __this__messageList;
    }
  }

  @Override
  public TimMessageList deepCopy() {
    return new TimMessageList(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.messageList = null;
  }

  public long getId() {
    return this.id;
  }

  public TimMessageList setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getMessageListSize() {
    return (this.messageList == null) ? 0 : this.messageList.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<TimMessage> getMessageListIterator() {
    return (this.messageList == null) ? null : this.messageList.iterator();
  }

  public void addToMessageList(TimMessage elem) {
    if (this.messageList == null) {
      this.messageList = new java.util.ArrayList<TimMessage>();
    }
    this.messageList.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<TimMessage> getMessageList() {
    return this.messageList;
  }

  public TimMessageList setMessageList(@org.apache.thrift.annotation.Nullable java.util.List<TimMessage> messageList) {
    this.messageList = messageList;
    return this;
  }

  public void unsetMessageList() {
    this.messageList = null;
  }

  /** Returns true if field messageList is set (has been assigned a value) and false otherwise */
  public boolean isSetMessageList() {
    return this.messageList != null;
  }

  public void setMessageListIsSet(boolean value) {
    if (!value) {
      this.messageList = null;
    }
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case MESSAGE_LIST:
      if (value == null) {
        unsetMessageList();
      } else {
        setMessageList((java.util.List<TimMessage>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case MESSAGE_LIST:
      return getMessageList();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case MESSAGE_LIST:
      return isSetMessageList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that instanceof TimMessageList)
      return this.equals((TimMessageList)that);
    return false;
  }

  public boolean equals(TimMessageList that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_messageList = true && this.isSetMessageList();
    boolean that_present_messageList = true && that.isSetMessageList();
    if (this_present_messageList || that_present_messageList) {
      if (!(this_present_messageList && that_present_messageList))
        return false;
      if (!this.messageList.equals(that.messageList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetId()) ? 131071 : 524287);
    if (isSetId())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

    hashCode = hashCode * 8191 + ((isSetMessageList()) ? 131071 : 524287);
    if (isSetMessageList())
      hashCode = hashCode * 8191 + messageList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TimMessageList other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.compare(isSetId(), other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.compare(isSetMessageList(), other.isSetMessageList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMessageList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.messageList, other.messageList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TimMessageList(");
    boolean first = true;

    if (isSetId()) {
      sb.append("id:");
      sb.append(this.id);
      first = false;
    }
    if (isSetMessageList()) {
      if (!first) sb.append(", ");
      sb.append("messageList:");
      if (this.messageList == null) {
        sb.append("null");
      } else {
        sb.append(this.messageList);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TimMessageListStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TimMessageListStandardScheme getScheme() {
      return new TimMessageListStandardScheme();
    }
  }

  private static class TimMessageListStandardScheme extends org.apache.thrift.scheme.StandardScheme<TimMessageList> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, TimMessageList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MESSAGE_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list152 = iprot.readListBegin();
                struct.messageList = new java.util.ArrayList<TimMessage>(_list152.size);
                @org.apache.thrift.annotation.Nullable TimMessage _elem153;
                for (int _i154 = 0; _i154 < _list152.size; ++_i154)
                {
                  _elem153 = new TimMessage();
                  _elem153.read(iprot);
                  struct.messageList.add(_elem153);
                }
                iprot.readListEnd();
              }
              struct.setMessageListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, TimMessageList struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetId()) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeI64(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.messageList != null) {
        if (struct.isSetMessageList()) {
          oprot.writeFieldBegin(MESSAGE_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.messageList.size()));
            for (TimMessage _iter155 : struct.messageList)
            {
              _iter155.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TimMessageListTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TimMessageListTupleScheme getScheme() {
      return new TimMessageListTupleScheme();
    }
  }

  private static class TimMessageListTupleScheme extends org.apache.thrift.scheme.TupleScheme<TimMessageList> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TimMessageList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetMessageList()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetMessageList()) {
        {
          oprot.writeI32(struct.messageList.size());
          for (TimMessage _iter156 : struct.messageList)
          {
            _iter156.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TimMessageList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list157 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRUCT);
          struct.messageList = new java.util.ArrayList<TimMessage>(_list157.size);
          @org.apache.thrift.annotation.Nullable TimMessage _elem158;
          for (int _i159 = 0; _i159 < _list157.size; ++_i159)
          {
            _elem158 = new TimMessage();
            _elem158.read(iprot);
            struct.messageList.add(_elem158);
          }
        }
        struct.setMessageListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
