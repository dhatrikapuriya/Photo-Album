using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
namespace Practise_sem
{
    #region Gallery
    public class Gallery
    {
        #region Member Variables
        protected int _Number;
        protected string _path;
        #endregion
        #region Constructors
        public Gallery() { }
        public Gallery(string path)
        {
            this._path=path;
        }
        #endregion
        #region Public Properties
        public virtual int Number
        {
            get {return _Number;}
            set {_Number=value;}
        }
        public virtual string Path
        {
            get {return _path;}
            set {_path=value;}
        }
        #endregion
    }
    #endregion
}