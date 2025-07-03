package com.example.anmproject.databinding;
import com.example.anmproject.R;
import com.example.anmproject.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProfileBindingImpl extends FragmentProfileBinding implements com.example.anmproject.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textView4, 4);
        sViewsWithIds.put(R.id.textInputLayout13, 5);
        sViewsWithIds.put(R.id.textOldPassword, 6);
        sViewsWithIds.put(R.id.textInputLayout12, 7);
        sViewsWithIds.put(R.id.textNewPassword, 8);
        sViewsWithIds.put(R.id.textInputLayout14, 9);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener textConfirmPassandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of password
            //         is com.example.anmproject.databinding.FragmentProfileBindingImpl.this.setPassword(callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(textConfirmPass);
            // localize variables for thread safety
            // password
            java.lang.String password = mPassword;


            if ((com.example.anmproject.databinding.FragmentProfileBindingImpl.this) != (null)) {



                com.example.anmproject.databinding.FragmentProfileBindingImpl.this.setPassword(callbackArg_0);
            }
        }
    };

    public FragmentProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private FragmentProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[3]
            , (android.widget.Button) bindings[2]
            , (com.google.android.material.textfield.TextInputEditText) bindings[1]
            , (com.google.android.material.textfield.TextInputLayout) bindings[7]
            , (com.google.android.material.textfield.TextInputLayout) bindings[5]
            , (com.google.android.material.textfield.TextInputLayout) bindings[9]
            , (com.google.android.material.textfield.TextInputEditText) bindings[8]
            , (com.google.android.material.textfield.TextInputEditText) bindings[6]
            , (android.widget.TextView) bindings[4]
            );
        this.buttonChangePassword.setTag(null);
        this.buttonSignOut.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textConfirmPass.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.example.anmproject.generated.callback.OnClickListener(this, 1);
        mCallback2 = new com.example.anmproject.generated.callback.OnClickListener(this, 2);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.password == variableId) {
            setPassword((java.lang.String) variable);
        }
        else if (BR.editListener == variableId) {
            setEditListener((com.example.anmproject.view.UserEditListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPassword(@Nullable java.lang.String Password) {
        this.mPassword = Password;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.password);
        super.requestRebind();
    }
    public void setEditListener(@Nullable com.example.anmproject.view.UserEditListener EditListener) {
        this.mEditListener = EditListener;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.editListener);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String password = mPassword;
        com.example.anmproject.view.UserEditListener editListener = mEditListener;

        if ((dirtyFlags & 0x5L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.buttonChangePassword.setOnClickListener(mCallback2);
            this.buttonSignOut.setOnClickListener(mCallback1);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.textConfirmPass, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, textConfirmPassandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textConfirmPass, password);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // editListener != null
                boolean editListenerJavaLangObjectNull = false;
                // editListener
                com.example.anmproject.view.UserEditListener editListener = mEditListener;



                editListenerJavaLangObjectNull = (editListener) != (null);
                if (editListenerJavaLangObjectNull) {



                    editListener.signOutListener(callbackArg_0);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // password
                java.lang.String password = mPassword;
                // editListener != null
                boolean editListenerJavaLangObjectNull = false;
                // editListener
                com.example.anmproject.view.UserEditListener editListener = mEditListener;



                editListenerJavaLangObjectNull = (editListener) != (null);
                if (editListenerJavaLangObjectNull) {




                    editListener.onUserEditPassListener(callbackArg_0, password);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): password
        flag 1 (0x2L): editListener
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}